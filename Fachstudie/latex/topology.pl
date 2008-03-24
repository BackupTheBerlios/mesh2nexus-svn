#!/usr/bin/perl

use IO::Socket;

$TOP_PATH = "/var/www/html/topology";
$WWW_PATH = "/topology";
$SERVER = "localhost";
$PORT = "8081";
$DOT_FILENAME = "topology.dot";
$IMAGE_TYPE = "png";
$IMAGE_FILENAME = "topology.$IMAGE_TYPE";
$IMAGE_BGCOLOR = "grey";
$IMAGE_SIZE = "35,20";

$remote = IO::Socket::INET->new(Proto => "tcp",
	PeerAddr => $SERVER, PeerPort => $PORT) ||
	die "couldn't connect to dot server\n";

open DOT_FILE, ">$TOP_PATH/$DOT_FILENAME" ||
        die "couldn't create $DOT_FILENAME\n";

while ($line = <$remote>) {
        print DOT_FILE $line;

        if ($line =~ /}/i) {
                last;
        }
}

close DOT_FILE;

`dot -T$IMAGE_TYPE -Gsize=$IMAGE_SIZE -Gbgcolor=$IMAGE_BGCOLOR\
	-o $TOP_PATH/$IMAGE_FILENAME $TOP_PATH/$DOT_FILENAME`;

$date = `date`;

print<<EOF
Content-type: text/html\r
\r

<HTML>
<HEAD>
<TITLE>Topology</TITLE>
</HEAD>
<BODY>

<CENTER>

<H1>Topology</H1>

<H2>$date</H2>

<IMG SRC="$WWW_PATH/$IMAGE_FILENAME">

<BR><BR>

<A HREF="http://pcvs63.informatik.uni-stuttgart.de:8080">
OLSRD-Seite von pcvs63
</A>

</CENTER>

</BODY>

</HTML>
EOF
