CXXFLAGS += --std=c++20

list_temps: list_temps.o
	$(CXX) $(CXXFLAGS) list_temps.o -o list_temps

list_temps.o: list_temps.cpp Date.h
	$(CXX) $(CXXFLAGS) -c list_temps.cpp -o list_temps.o

clean:
	rm -f *.o list_temps