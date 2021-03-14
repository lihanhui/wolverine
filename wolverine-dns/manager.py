import cache
import record

class Manager():
    def __init__(self):
       self.cache = cache.Cache()
       self.cache.start()
    def getRecord(self, hostname):
        return self.cache.getRecord(hostname)
 
    def getRecords(self, service):
        return self.cache.getRecords(servicename)
