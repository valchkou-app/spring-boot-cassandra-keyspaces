package com.valchkou.demo.model

import org.springframework.cassandra.core.PrimaryKeyType
import org.springframework.data.cassandra.mapping.Column
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn
import org.springframework.data.cassandra.mapping.Table

@Table(value = "sensor_measures_simple")
class SensorMeasureEntity {

    @PrimaryKeyColumn(name = "sensor_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    Integer sensorId

    @PrimaryKeyColumn(name = "measure_time", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    Date measureTime = new Date()

    @Column(value = "measure_type")
    MeasureType measureType

    @Column(value = "measure_value")
    Double measureValue

}