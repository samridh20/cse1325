CXX = g++
CXXFLAGS = --std=c++20

bank: bank.o purse.o
	$(CXX) $(CXXFLAGS) bank.o purse.o -o bank

bank.o: bank.cpp Purse.h
	$(CXX) $(CXXFLAGS) -c bank.cpp -o bank.o

purse.o: Purse.cpp Purse.h
	$(CXX) $(CXXFLAGS) -c Purse.cpp -o purse.o

clean:
	rm -f *.o bank