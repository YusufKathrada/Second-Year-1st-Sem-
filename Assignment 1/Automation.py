import os

originalData = open("vaccinations.txt","r")

#Set 1
subset1 = originalData.readlines()[0:8928]                  #Readlines() range changes in order to form different subsets
listToStr1 = ''.join([str(elem) for elem in subset1])       

Experiment1 = open("Experiment9.txt","a")                   #Outputs it to specified .txt file which can then be used as input for testing VAA and VBSTA
Experiment1.write(listToStr1)
Experiment1.close()

