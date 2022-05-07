# 1 Check os version in Linux

```shell
1 cat /etc/os-release
2 lsb_release -a
3 hostnamectl
```



# 2 How to setup the JAVA_HOME path in Debian

```shell
vim /etc/profile.d/jdk.env.sh

export JAVA_HOME=/home/tigerjsh/java-se-8u41-ri
export PATH=$PATH:$JAVA_HOME/bin
```

