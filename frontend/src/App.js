import './App.css';
import DiceList from './DiceList';
import { useState } from 'react';
// 1. import `ChakraProvider` component
import { ChakraProvider } from '@chakra-ui/react'
import { Heading } from '@chakra-ui/react'
import axios from 'axios';
import { Card, CardHeader, CardBody, CardFooter, Text } from '@chakra-ui/react'
import { NumberInput, NumberInputField, NumberInputStepper, NumberIncrementStepper, NumberDecrementStepper, Button } from '@chakra-ui/react'


function App() {
  const [value, setValue] = useState(6)

  const baseURL = "http://localhost:8081/dices";
  function handleClick(sizeOfTheDice) {
    //alert('You clicked me!' + sizeOfTheDice);
    if (sizeOfTheDice !== null)
      axios.post(baseURL + "?size=" + sizeOfTheDice)
        .then(
          (response) => {
            console.log(response);
            // Update page
            window.location.reload(false);
            //forceUpdate();
          }
        );
  }

  return (
    <ChakraProvider>
      <div className="App">
        <header className="App-header">
          <Heading size="xl" variant="underline">
            Dice-Web-App
          </Heading>
        </header>
        <div className="history-header">
          <Card>
            <CardHeader>
              <Heading>
                Choose Dice to Roll
              </Heading>
            </CardHeader>
            <CardBody>
              <Text py='2'>
                Size of the dice:
              </Text>
              <NumberInput defaultValue={6} min={2} max={1000} value={value} onChange={(newValue) => setValue(newValue)}>
                <NumberInputField />
                <NumberInputStepper>
                  <NumberIncrementStepper />
                  <NumberDecrementStepper />
                </NumberInputStepper>
              </NumberInput>
            </CardBody>
            <CardFooter>
              <Button variant='solid' colorScheme='blue' onClick={() => handleClick(value)}>
                Roll
              </Button>
            </CardFooter>
          </Card>
        </div>

        <div className="history-header">
          <Heading>
            History of rolled Dices
          </Heading>
        </div>
        <DiceList></DiceList>
      </div>
    </ChakraProvider>
  );
}

export default App;
