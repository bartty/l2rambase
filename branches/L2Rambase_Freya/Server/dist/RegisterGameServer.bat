@echo off
color 17
cls
java -Djava.util.logging.config.file=console.cfg -cp ./../libs/*;l2rambaselogin.jar l2.rambase.gsregistering.BaseGameServerRegister -c
exit