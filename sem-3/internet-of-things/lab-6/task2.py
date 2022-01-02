import RPi.GPIO as GPIO
from time import sleep
GPIO.setmode(GPIO.BCM)
GPIO.setwarnings(False)
GPIO.setup(2,GPIO.OUT)
GPIO.output(2,False)
GPIO.setup(2,GPIO.IN)
GPIO.setup(18,GPIO.OUT)
while True:
	ldr = GPIO.input(2)
	if ldr==1:
		GPIO.output(18,GPIO.HIGH)
	else:
		GPIO.output(18,GPIO.LOW)
	sleep(0.1)
