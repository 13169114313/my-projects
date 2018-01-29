set myself=%~dp0
set dist_root=%myself%.\
set dist_bin=%myself%.\blogger
set dist_lib=%dist_bin%\lib
set dist_etc=%dist_root%\etc

pushd %dist_root%
call java -Dfile.encoding=UTF-8 -cp %dist_lib%\*;%dist_bin%\* com.blogger.Application %dist_bin% --spring.config.location=%dist_bin%\application.properties
popd

pause