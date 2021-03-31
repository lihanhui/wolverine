from threading import Thread
import socket
import constant
import json

class UdpServer(Thread):
    def __init__(self, manager):
        Thread.__init__(self)
        self.manager = manager
        # Create a datagram socket
        self.UDPServerSocket = socket.socket(family=socket.AF_INET, type=socket.SOCK_DGRAM)

        # Bind to address and ip
        self.UDPServerSocket.bind((constant.localIP, constant.localPort))
    def handle(self, packet):
        result = {"status": 200}
        if packet["method"] == "translate-domain-name":
            result["result"] = self.manager.getRecord(packet["hostname"])
        elif packet["method"] == "translate-service-name":
            result["result"] = self.manager.getRecords(packet["servicename"])
        else:
            result["status"] = 401
        
        return result 
        
    def run(self):
        while(True):
            data, addr = self.UDPServerSocket.recvfrom(constant.bufferSize)
            packet = str(data)
            print(packet)
            packet = json.loads(packet)
            result = self.handle(packet)
            # result = {"ok": "ok"}
            # Sending a reply to client - bytes(test_string, 'utf-8') 
            self.UDPServerSocket.sendto(str.encode(json.dumps(result)), addr)
        
