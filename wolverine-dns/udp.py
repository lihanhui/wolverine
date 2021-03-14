from threading import Thread
import socket
import constant

class UdpServer(Thread):
    def __init__(self, manager):
        Thread.__init__(self)
        self.manager = manager
        # Create a datagram socket
        self.UDPServerSocket = socket.socket(family=socket.AF_INET, type=socket.SOCK_DGRAM)

        # Bind to address and ip
        self.UDPServerSocket.bind((constant.localIP, constant.localPort))

    def run(self):
        while(True):
            data, addr = self.UDPServerSocket.recvfrom(constant.bufferSize)
            print(str(data))
            print(addr)

            # Sending a reply to client
            self.UDPServerSocket.sendto(data, addr)
        
