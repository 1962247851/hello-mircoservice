FROM mysql/mysql-server:8.0.26

MAINTAINER lengleng(wangiegie@gmail.com)

ENV TZ=Asia/Shanghai

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

COPY ./ordinaryroad.sql /docker-entrypoint-initdb.d

COPY ./ordinaryroad_codegen.sql /docker-entrypoint-initdb.d

COPY ./ordinaryroad_config.sql /docker-entrypoint-initdb.d

COPY ./ordinaryroad_job.sql /docker-entrypoint-initdb.d
