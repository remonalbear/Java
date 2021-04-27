line=""
with open('genes.txt','r') as f:
    line = f.readline()
    line=line.upper()
    f.close() 
print(line)
with open('genes.txt', 'w') as f:
    f.write(line)
    f.close()