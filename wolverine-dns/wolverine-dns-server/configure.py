import json

class Config():
    conf = None
    def __init__(self, jsonFile):
        f = open(jsonFile,) 
        self.conf = json.load(f)
        self.masterUri = None
        Config.conf = self
        

    @staticmethod    
    def getConfig():
        return Config.conf

    def getVal(self, key):
        return self.conf[key]

    def getMasterUri(self):
        return self.masterUri;

    def setMasterUri(self, masterUri):
        self.masterUri = masterUri
