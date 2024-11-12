CXX = g++
CXXFLAGS = -std=c++20


bank: bank.o purse.o
	$(CXX) $(CXXFLAGS) bank.o purse.o -o bank

bank.o: bank.cpp Purse.h
	$(CXX) $(CXXFLAGS) -c bank.cpp -o bank.o

purse.o: Purse.cpp Purse.h
	$(CXX) $(CXXFLAGS) -c Purse.cpp -o purse.o


test_extreme_bonus_pound: test_extreme_bonus_pound.o purse.o
	$(CXX) $(CXXFLAGS) test_extreme_bonus_pound.o purse.o -o test_extreme_bonus_pound

test_extreme_bonus_pound.o: test_extreme_bonus_pound.cpp Purse.h
	$(CXX) $(CXXFLAGS) -c test_extreme_bonus_pound.cpp -o test_extreme_bonus_pound.o


test_extreme_bonus_subscripts: test_extreme_bonus_subscripts.o purse.o
	$(CXX) $(CXXFLAGS) test_extreme_bonus_subscripts.o purse.o -o test_extreme_bonus_subscripts

test_extreme_bonus_subscripts.o: test_extreme_bonus_subscripts.cpp Purse.h
	$(CXX) $(CXXFLAGS) -c test_extreme_bonus_subscripts.cpp -o test_extreme_bonus_subscripts.o


all: bank test_extreme_bonus_pound test_extreme_bonus_subscripts

clean:
	rm -f *.o bank test_extreme_bonus_pound test_extreme_bonus_subscripts