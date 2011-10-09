@echo off
color 17
cls
java -Djava.util.logging.config.file=console.cfg -cp ./../libs/*;l2Rambase_login.jar l2.Rambase.gsregistering.BaseGameServerRegister -c
exit