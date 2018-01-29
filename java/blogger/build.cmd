set myself=%~dp0


set src_etc=%myself%src\main\resources
set src_lib=%myself%lib
set src_jar=%myself%target


set dist_jar=%myself%..\..\..\release\blogger
set dist_jar_lib=%dist_jar%\lib


xcopy %src_etc% %dist_jar% /E /Y /I
xcopy	%src_lib% %dist_jar_lib% /E /Y /I
xcopy	%src_jar%\blogger* %dist_jar% /Y
xcopy	%myself%start.cmd %dist_jar%\..\ /Y

pause