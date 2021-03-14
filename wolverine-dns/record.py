
class Record():
    def __init__(self, hostname, service, addr):
        self.hostname = hostname
        self.service  = service
        self.addr     = addr
    def getHostname(self):
        return self.hostname

    def getService(self):
        return self.service

    def getAddr(self):
        return self.addr
