CC = gcc
CFLAGS = -Wall -Wextra
OBJECTS = main.o projet2.o

all: main

main: $(OBJECTS)
	$(CC) $(CFLAGS) -o main $(OBJECTS)

main.o: main.c projet.h
	$(CC) $(CFLAGS) -c main.c

projet2.o: projet2.c projet.h
	$(CC) $(CFLAGS) -c projet2.c

clean:
	rm -f main $(OBJECTS)