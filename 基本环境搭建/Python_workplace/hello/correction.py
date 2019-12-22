#print("Hello World Typo")
import sys
# import pycorrector

def typo(text):
    #corrected_sent, detail = pycorrector.correct(text)
    print("Typo Analyse: received:" + text + "result:")

if __name__ == '__main__':
    #print(sys.argv[0])
    #print(sys.argv[1])
    typo(sys.argv[1])
