cmake_minimum_required(VERSION 3.5)
project(Matala6)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES main.cpp CFile.h CElement.cpp CElement.h)
add_executable(Matala6 ${SOURCE_FILES})