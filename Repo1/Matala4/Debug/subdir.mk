################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../FarmProduct.cpp \
../FruitProduct.cpp \
../Product.cpp \
../ShelfRow.cpp 

OBJS += \
./FarmProduct.o \
./FruitProduct.o \
./Product.o \
./ShelfRow.o 

CPP_DEPS += \
./FarmProduct.d \
./FruitProduct.d \
./Product.d \
./ShelfRow.d 


# Each subdirectory must supply rules for building sources it contributes
%.o: ../%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -I/usr/include/c++ -I/usr/include -I/home/orenk/ProductionCode/Repo/Matala4 -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


