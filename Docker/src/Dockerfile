FROM alpine:edge
MAINTAINER antonynirmal@outlook.com
RUN apk add --no-cache openjdk8
COPY ./account-transaction-0.0.1-SNAPSHOT.jar /opt/account-transaction/lib/
ENTRYPOINT ["/usr/bin/java"]
CMD ["-jar", "/opt/account-transaction/lib/account-transaction-0.0.1-SNAPSHOT.jar"]
VOLUME /var/lib/account-transaction/config-repo
EXPOSE 8080
