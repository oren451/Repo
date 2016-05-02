################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src/CheeseProduct.cpp \
../src/FarmProduct.cpp \
../src/FruitProduct.cpp \
../src/MilkProduct.cpp \
../src/OtherMilkProduct.cpp \
../src/PackageProduct.cpp \
../src/Product.cpp \
../src/Store.cpp \
../src/Task4New.cpp \
../src/VegtableProduct.cpp 

OBJS += \
./src/CheeseProduct.o \
./src/FarmProduct.o \
./src/FruitProduct.o \
./src/MilkProduct.o \
./src/OtherMilkProduct.o \
./src/PackageProduct.o \
./src/Product.o \
./src/Store.o \
./src/Task4New.o \
./src/VegtableProduct.o 

CPP_DEPS += \
./src/CheeseProduct.d \
./src/FarmProduct.d \
./src/FruitProduct.d \
./src/MilkProduct.d \
./src/OtherMilkProduct.d \
./src/PackageProduct.d \
./src/Product.d \
./src/Store.d \
./src/Task4New.d \
./src/VegtableProduct.d 


# Each subdirectory must supply rules for building sources it contributes
src/%.o: ../src/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


