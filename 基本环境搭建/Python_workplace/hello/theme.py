import sys

def theme(text):
    print("Theme Analyse: received:" + text)

if __name__ == '__main__':
    theme(sys.argv[1])