# spring-boot-cassandra-simple

## Set Up Instructions
This project requires access to Cassandra DB. 
Default is localhost. If you need point to different one you should modify properties resources/application.yaml. 

Script to Create schema and table. 

```sql
drop schema if exists sensor_demo_a;
CREATE schema if not exists sensor_demo_a WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1}  AND durable_writes = false;

drop schema if exists sensor_demo_b;
CREATE schema if not exists sensor_demo_b WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1}  AND durable_writes = false;

drop table if exists sensor_demo_a.sensor_measures_simple;
CREATE TABLE sensor_demo_a.sensor_measures_simple (
    sensor_id int,
	measure_time timestamp,
	measure_type text,
    measure_value double,
	PRIMARY KEY (sensor_id, measure_time)
) WITH CLUSTERING ORDER BY (measure_time DESC)
    AND comment = 'Sensor Measures'
    AND compaction = {'class': 'LeveledCompactionStrategy'}
    AND compression = {'class': 'SnappyCompressor'}
    AND default_time_to_live = 0
    AND gc_grace_seconds = 0;
    
drop table if exists sensor_demo_b.sensor_measures_simple;
CREATE TABLE sensor_demo_b.sensor_measures_simple (
    sensor_id int,
	measure_time timestamp,
	measure_type text,
    measure_value double,
	PRIMARY KEY (sensor_id, measure_time)
) WITH CLUSTERING ORDER BY (measure_time DESC)
    AND comment = 'Sensor Measures'
    AND compaction = {'class': 'LeveledCompactionStrategy'}
    AND compression = {'class': 'SnappyCompressor'}
    AND default_time_to_live = 0
    AND gc_grace_seconds = 0
```
