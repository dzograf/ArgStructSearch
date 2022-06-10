FROM ubuntu:kinetic

ENV VIRT_HOME /opt/virtuoso-opensource

ENV VIRT_BUILD_OPTS --with-readline

ENV VIRT_DB /var/lib/virtuoso/db

RUN apt-get -y update && \
  apt-get -y clean && \
  apt-get -y install dpkg-dev build-essential && \
  apt-get -y install autoconf automake libtool flex bison git gperf gawk m4 make libxml2-dev libssl-dev libreadline-dev wget && \
  rm -rf /var/lib/apt/lists/* && \
  rm -rf /tmp/*

WORKDIR /opt
RUN git clone http://github.com/openlink/virtuoso-opensource.git virtuoso-opensource.src
WORKDIR /opt/virtuoso-opensource.src
RUN git checkout develop/7
RUN ./autogen.sh && ./configure --prefix=$VIRT_HOME $VIRT_BUILD_OPTS && make && make install

WORKDIR /opt/virtuoso-opensource/share/virtuoso/vad
RUN wget http://download3.openlinksw.com/uda/vad-packages/7.2/cartridges_dav.vad

ENV VIRT_HOME /opt/virtuoso-opensource
ENV VIRT_DB /opt/virtuoso-opensource/var/lib/virtuoso/db

RUN export PATH=$PATH:/opt/virtuoso-opensource/bin

EXPOSE 1111
EXPOSE 8890

WORKDIR $VIRT_DB
CMD /opt/virtuoso-opensource/bin/virtuoso-t -f
