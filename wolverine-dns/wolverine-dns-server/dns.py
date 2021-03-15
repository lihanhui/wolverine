import socket
import udp
import sys
import configure
import manager

def main(conf):
    json = configure.Config(conf)
    udpServer = udp.UdpServer(manager.Manager());
    udpServer.start()
    udpServer.join();
 	
if __name__ == "__main__":
    if len(sys.argv) == 2:
        config = sys.argv[1]
        try:
            main(config)
        except KeyboardInterrupt:
            # quit
            sys.exit()
