import './App.css';
import DiceList from './DiceList';
import { useState } from 'react';
// 1. import `ChakraProvider` component
import { ChakraProvider } from '@chakra-ui/react'
import { Heading } from '@chakra-ui/react'
import axios from 'axios';
import { Card, CardHeader, CardBody, CardFooter, Text } from '@chakra-ui/react'
import { NumberInput, NumberInputField, NumberInputStepper, NumberIncrementStepper, NumberDecrementStepper, Button } from '@chakra-ui/react'
import { Slider, SliderFilledTrack, SliderTrack, SliderThumb, HStack, Spacer } from '@chakra-ui/react'

function App() {
  const [value, setValue] = useState(6)
  const [renderDiceListKey, setRenderDiceListKey] = useState(0)

  //const baseURL = "http://localhost:8081";
  const baseURL = "https://8081-ralfze-ws2324webapp-pjfmlslo17m.ws-eu106.gitpod.io";
  //const baseURL = process.env.BASE_URL;
  function handleClick(sizeOfTheDice) {
    if (sizeOfTheDice !== null)
      axios.post(`${baseURL}/dices?size=${sizeOfTheDice}`, { withCredentials: true })
        .then(
          (response) => {
            console.log(response);
            // Update render key to update the dice list
            setRenderDiceListKey((renderDiceListKey) => renderDiceListKey + 1);
          })
        .catch((error) => {
          console.error('Error while making the API call:', error);
        });
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
              <HStack>
                <NumberInput defaultValue={6} min={2} max={1000} value={value} onChange={(newValue) => setValue(newValue)} maxW='100px'>
                  <NumberInputField />
                  <NumberInputStepper>
                    <NumberIncrementStepper />
                    <NumberDecrementStepper />
                  </NumberInputStepper>
                </NumberInput>
                <Spacer />
                <Slider
                  flex='100'
                  focusThumbOnChange={false}
                  value={value}
                  onChange={(newValue) => setValue(newValue)}
                  min={2}
                  max={1000}
                >
                  <SliderTrack>
                    <SliderFilledTrack />
                  </SliderTrack>
                  <SliderThumb fontSize='sm' boxSize='32px' children={value} />
                </Slider>
              </HStack>
            </CardBody>
            <CardFooter>
              <Button variant='solid' colorScheme='blue' onClick={() => handleClick(value)}>
                &nbsp;Roll&nbsp;
              </Button>
            </CardFooter>
          </Card>
        </div>

        <div className="history-header">
          <Heading>
            History of rolled Dices
          </Heading>
        </div>
        <DiceList key={renderDiceListKey} baseURL={baseURL}></DiceList>
      </div>
    </ChakraProvider>
  );
}

export default App;
