
from PIL import Image
import pytesseract

def process_image(image_name, lang_code):
	return pytesseract.image_to_string(Image.open(image_name), lang=lang_code)

def output_file(filename, data):
	file = open(filename, "w+")
	file.write(data)
	file.close()

def main():
	text = process_image("test-img.jpeg", "eng")
	print(text)
	output_file("text.txt", text)

if  __name__ == '__main__':
	main()