import json

class Config():
    config = None
    def __init__(self, jsonFile):
        f = open(jsonFile,) 
        self.conf = json.load(f)
        config = self

    @staticmethod    
    def getConfig():
        return config
