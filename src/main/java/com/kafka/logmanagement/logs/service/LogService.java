package com.kafka.logmanagement.logs.service;

import com.kafka.logmanagement.logs.exception.LogNotFoundException;
import com.kafka.logmanagement.logs.repository.LogRepository;
import com.kafka.logmanagement.logs.model.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for handling business logic related to logs.
 * Provides methods for listing, saving, retrieving, and deleting logs.
 */
@Service
public class LogService {

    @Autowired
    private LogRepository repo;

    /**
     * Retrieves all logs from the repository.
     *
     * @return a list of logs
     */
    public List<Log> listAll() {
        return (List<Log>) repo.findAll();
    }

    /**
     * Saves a log entry to the repository.
     *
     * @param log the log entry to save
     */
    public void save(Log log) {
        repo.save(log);
    }

    /**
     * Retrieves a log entry by its ID.
     *
     * @param id the ID of the log entry to retrieve
     * @return the log entry
     * @throws LogNotFoundException if the log entry is not found
     */
    public Log get(Integer id) throws LogNotFoundException {
        Optional<Log> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new LogNotFoundException("Could not find any logs with ID " + id);
    }

    /**
     * Deletes a log entry by its ID.
     *
     * @param id the ID of the log entry to delete
     * @throws LogNotFoundException if the log entry is not found
     */
    public void delete(Integer id) throws LogNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new LogNotFoundException("Could not find any logs with ID " + id);
        }
        repo.deleteById(id);
    }

    /**
     * Retrieves logs from the repository based on dynamic filters.
     *
     * @param timestamp the timestamp to filter by (optional)
     * @param status    the status to filter by (optional)
     * @param topic     the topic to filter by (optional)
     * @param partition the partition to filter by (optional)
     * @param offset    the offset to filter by (optional)
     * @param key       the key to filter by (optional)
     * @param userId    the userId to filter by (optional)
     * @param server    the server to filter by (optional)
     * @return a list of logs matching the filters
     */
    public List<Log> findLogsByDynamicFilter(String timestamp, String status, String topic, Integer partition, Integer offset, String key, Integer userId, String server) {
        return repo.findByFilters(timestamp, status, topic, partition, offset, key, userId, server);
    }
}
