package com.valchkou.demo.repo.keyspaceB

import com.valchkou.demo.model.SensorMeasureEntity
import org.springframework.data.cassandra.repository.CassandraRepository
import org.springframework.data.cassandra.repository.Query
import org.springframework.stereotype.Repository

import java.util.stream.Stream

@Repository
interface ISensorMeasureRepositoryB extends CassandraRepository<SensorMeasureEntity> {

    @Query('select * from sensor_measures_simple where sensor_id=?0 and measure_time>=?1 and measure_time<=?2')
    List<SensorMeasureEntity> getBySensorAndDateRange(int sensorId, Date start, Date end)

    @Query('select * from sensor_measures_simple where sensor_id=?0 ALLOW FILTERING')
    Stream<SensorMeasureEntity> getAllBySensor(int sensorId)

}
