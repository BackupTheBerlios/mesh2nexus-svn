#!/bin/bash
#
# Startup script for the OLSR Daemon
#
# chkconfig: 235 16 84
# description: This script starts OLSRD (Ad Hoc routing protocol)
#
# processname: olsrd
# config: %{_sysconfdir}/olsrd.conf
# pidfile: %{_localstatedir}/run/olsrd.pid

source /etc/init.d/functions
source /etc/sysconfig/network

# Check that networking is up.
[ ${NETWORKING} = "no" ] && exit 0

[ -x /usr/sbin/olsrd ] || exit 1
[ -r /etc/olsrd.conf ] || exit 1

RETVAL=0
prog="olsrd"
desc="Ad Hoc routing protocol"

start() {
        echo -n $"Starting $desc ($prog): "
        daemon $prog -d 0
        RETVAL=$?
        echo
        [ $RETVAL -eq 0 ] && touch /var/lock/subsys/$prog
        return $RETVAL
}

stop() {
        echo -n $"Shutting down $desc ($prog): "
        killproc $prog
        RETVAL=$?
        echo
        [ $RETVAL -eq 0 ] && rm -f /var/lock/subsys/$prog
        return $RETVAL
}

reload() {
        echo -n $"Reloading $desc ($prog): "
        killproc $prog -HUP
        RETVAL=$?
        echo
        return $RETVAL
}

restart() {
        stop
        start
}


case "$1" in
  start)
        start
        ;;
  stop)
        stop
        ;;
  restart)
        restart
        ;;
  reload)
        reload
        ;;
  condrestart)
        [ -e /var/lock/subsys/$prog ] && restart
        RETVAL=$?
        ;;
  status)
        status olsrd
        ;;
  *)
        echo $"Usage $0 {start|stop|restart|reload|\
		condrestart|status}"
        RETVAL=1
esac

exit $RETVAL
