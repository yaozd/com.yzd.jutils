- [linux下查看CPU、内存、磁盘信息](https://www.cnblogs.com/xubiao/p/6497533.html)

- 1、查看CPU信息
```
1、查看CPU信息
# 总核数 = 物理CPU个数 X 每颗物理CPU的核数
# 总逻辑CPU数 = 物理CPU个数 X 每颗物理CPU的核数 X 超线程数

# 查看物理CPU个数
cat /proc/cpuinfo| grep "physical id"| sort| uniq| wc -l

# 查看每个物理CPU中core的个数(即核数)
cat /proc/cpuinfo| grep "cpu cores"| uniq

# 查看逻辑CPU的个数
cat /proc/cpuinfo| grep "processor"| wc -l

# 查看CPU信息（型号）
cat /proc/cpuinfo | grep name | cut -f2 -d: | uniq -c
```
- 2、查看内存信息
```
2、查看内存信息
1）、cat /proc/meminfo
2）、free -h 命令
             total       used       free     shared    buffers     cached
Mem:      65973912   32496232   33477680          0     906932    6452984
-/+ buffers/cache:   25136316   40837596
Swap:     41943032      13204   41929828
```
- 3、查看磁盘信息
```
3、查看磁盘信息
1）fdisk -l
2）iostat -x 10    查看磁盘IO的性能
```