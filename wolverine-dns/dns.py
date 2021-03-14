import socket
import udp

localIP     = "0.0.0.0"
localPort   = 53
bufferSize  = 1024

msgFromServer       = "Hello UDP Client"
bytesToSend         = str.encode(msgFromServer)

def main():
    udpServer = udp.UdpServer(None);
    udpServer.start()
    udpServer.join();
 	
if __name__ == "__main__":
    main()
