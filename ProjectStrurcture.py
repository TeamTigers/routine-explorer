from os import listdir, sep
from os.path import abspath, basename, isdir
from sys import argv

def tree(dir, padding, print_files=False):
    print padding[:-1] + '+-' + basename(abspath(dir)) + '/'
    padding = padding + ' '
    files = []
    if print_files:
        files = listdir(dir)
    else:
        files = [x for x in listdir(dir) if isdir(dir + sep + x)]
    count = 0
    for file in files:
        count += 1
        print padding + '|'
        path = dir + sep + file
        if isdir(path):
            if count == len(files):
                tree(path, padding + ' ', print_files)
            else:
                tree(path, padding + '|', print_files)
        else:
            print padding + '+-' + file

def usage():
    return '''Usage: %s [-f] <PATH>
Print tree structure of path specified.
Options:
-f      Print files as well as directories
PATH    Path to process''' % basename(argv[0])

def main():
print ("├───build
│   ├───empty
│   ├───generated-sources
│   │   └───ap-source-output
│   └───web
│       ├───assets
│       │   ├───css
│       │   ├───demo
│       │   ├───img
│       │   ├───js
│       │   │   ├───core
│       │   │   └───plugins
│       │   └───scss
│       ├───META-INF
│       └───WEB-INF
│           ├───classes
│           │   └───com
│           │       └───FilesPack
│           └───lib
├───dist
├───lib
│   ├───CopyLibs
│   └───javaee-endorsed-api-7.0
├───nbproject
│   └───private
├───src
│   ├───conf
│   └───java
│       └───com
│           └───FilesPack
└───web
    ├───assets
    │   ├───css
    │   ├───demo
    │   ├───img
    │   ├───js
    │   │   ├───core
    │   │   └───plugins
    │   └───scss
    ├───META-INF
    └───WEB-INF")
