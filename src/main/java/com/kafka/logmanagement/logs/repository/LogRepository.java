package com.kafka.logmanagement.logs.repository;

import com.kafka.logmanagement.logs.model.Log;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository interface for accessing log entries in the database.
 */
public interface LogRepository extends CrudRepository<Log, Integer> {

    /**
     * Counts the number of log entries with the specified ID.
     *
     * @param id the ID of the log entries to count
     * @return the number of log entries with the specified ID
     */
    public Long countById(Integer id);

    public Long findByTimestamp(String topic);

    @Query("SELECT l FROM Log l " +
            "WHERE (:timestamp IS NULL OR l.timestamp = :timestamp) " +
            "AND (:status IS NULL OR l.status = :status) " +
            "AND (:topic IS NULL OR l.topic = :topic) " +
            "AND (:partition IS NULL OR l.partition = :partition) " +
            "AND (:offset IS NULL OR l.offset = :offset) " +
            "AND (:key IS NULL OR l.key = :key) " +
            "AND (:userId IS NULL OR l.userId = :userId) " +
            "AND (:server IS NULL OR l.server = :server)")
    public List<Log> findByFilters(
            @Param("timestamp") String timestamp,
            @Param("status") String status,
            @Param("topic") String topic,
            @Param("partition") Integer partition,
            @Param("offset") Integer offset,
            @Param("key") String key,
            @Param("userId") Integer userId,
            @Param("server") String server);

}
