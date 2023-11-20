import './App.css';
import DiceList from './DiceList';
// 1. import `ChakraProvider` component
import { ChakraProvider } from '@chakra-ui/react'
import { Heading } from '@chakra-ui/react'
import { Card, CardHeader, CardBody, CardFooter, Text } from '@chakra-ui/react'
import { NumberInput, NumberInputField, NumberInputStepper, NumberIncrementStepper, NumberDecrementStepper, Button} from '@chakra-ui/react'

function handleClick() {
  alert('You clicked me!');
}


function App() {
  return (
    <ChakraProvider>
      <div className="App">
        <header className="App-header">
          <Heading size="xl" variant="underline">
            Dice-Web-App
          </Heading>
        </header>
        <p className="history-header">

          <Card>
            <CardHeader>
              <Heading>
                Choose Dice to Roll
              </Heading>
            </CardHeader>
            <CardBody>
              <Text py='2'>
                Size of the dice:
                <NumberInput defaultValue={6} min={2} max={1000}>
                  <NumberInputField />
                  <NumberInputStepper>
                    <NumberIncrementStepper />
                    <NumberDecrementStepper />
                  </NumberInputStepper>
                </NumberInput>
              </Text>
            </CardBody>
            <CardFooter>
              <Button variant='solid' colorScheme='blue' onClick={handleClick}>
                Roll
              </Button>
            </CardFooter>
          </Card>
        </p>
        <p className="history-header">
          <Heading>
            History of rolled Dices
          </Heading>
        </p>
        <DiceList></DiceList>
      </div>
    </ChakraProvider>
  );
}

export default App;
