set backupFilename= plugin_sql_%DATE:~10,4%%DATE:~4,2%%DATE:~7,2%.sql
echo %backupFilename%
pg_dump -U postgres promotion_plugin > %backupFilename%