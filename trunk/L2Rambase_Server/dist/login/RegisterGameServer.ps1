$OutputEncoding = New-Object -typename System.Text.UTF8Encoding
$a = (Get-Host).UI.RawUI
$a.WindowTitle = "L2 Rambase - Register Game Server"
java "-Djava.util.logging.config.file=console.cfg" -cp "./../libs/*;l2Rambase_login.jar" l2.Rambase.tools.gsregistering.BaseGameServerRegister -c