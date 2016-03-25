# HomeProject
Bygges med Maven og med assembly-plugin: mvn clean install assembly:single
Kopieres til Raspberry Pi til /opt/energy/release.jar

Deployet til Raspberry Pi: 10.0.0.11
KWh lagres til /home/pi/data/kwh.txt
Satt opp med eigen service i /etc/init.d/energy
Service har start og stopscript i /usr/local/bin/energy-start.sh og energy-stop.sh
Logger lagres til /home/pi/data/log.txt og /home/pi/data/error.txt
