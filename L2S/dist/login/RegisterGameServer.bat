@echo off
color 0b
cls
java -Djava.util.logging.config.file=console.cfg -cp ./../libs/*;l2Rambase_login.jar l2.Rambase.tools.gsregistering.BaseGameServerRegister -c
exit