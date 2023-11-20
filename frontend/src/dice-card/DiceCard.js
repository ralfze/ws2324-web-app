import diceimg from '../img/dice_bg.png';
import '../App.css';

import { Card, CardBody, CardFooter, Text, Image, Stack, Heading, Button } from '@chakra-ui/react'
import { NumberInput, NumberInputField, NumberInputStepper, NumberIncrementStepper, NumberDecrementStepper } from '@chakra-ui/react'

function handleClick() {
    alert('You clicked me!');
  }

function DiceCard({dice}) {
    return (
        <Card
            direction={{ base: 'column', sm: 'row' }}
            overflow='hidden'
            variant='outline'
        >
            <Image
                objectFit='cover'
                maxW={{ base: '50%', sm: '256px' }}
                src={diceimg}
                alt='Caffe Latte'
            />

            <Stack>
                <CardBody>
                    <Heading size='md'>Dice d</Heading>

                    <Text py='2'>
                        Rolled Number: {dice.rolledNumber}
                    </Text>

                    <Text py='2'>
                        Size of the dice:
                        <NumberInput defaultValue={dice.sizeOfTheDice} min={2} max={1000}>
                            <NumberInputField />
                            <NumberInputStepper>
                                <NumberIncrementStepper />
                                <NumberDecrementStepper />
                            </NumberInputStepper>
                        </NumberInput>
                    </Text>

                    <Text py='2'>
                        Message: {dice.message}
                    </Text>

                    <Text py='2'>
                        Modified: {dice.modified}
                    </Text>

                    <Text py='2'>
                        Created: {dice.created}
                    </Text>
                </CardBody>

                <CardFooter>
                    <Button variant='solid' colorScheme='blue' onClick={handleClick}>
                        Re-roll
                    </Button>
                </CardFooter>
            </Stack>
        </Card>
    );
}

export default DiceCard