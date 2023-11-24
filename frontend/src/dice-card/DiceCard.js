import diceimg from '../img/dice_bg.png';
import '../App.css';
import axios from 'axios';
import { useState } from 'react';

import { Card, CardBody, CardFooter, Text, Image, Stack, Heading, Button } from '@chakra-ui/react'
import { NumberInput, NumberInputField, NumberInputStepper, NumberIncrementStepper, NumberDecrementStepper } from '@chakra-ui/react'
import { DeleteIcon } from '@chakra-ui/icons'
import { Flex, Spacer, VStack } from '@chakra-ui/react'


function DiceCard({ dice, baseURL, updateDiceList }) {
    // Function to handle Reroll
    function handleReroll() {
        //alert('You clicked me!');
        axios.put(baseURL + '/' + dice.id + '/reroll?sizeOfTheDice=' + sizeOfTheDice)
            .then(
                (response) => {
                    // console.log(response.data);
                    console.log(response.data);
                    updateDiceList();
                })
            .catch((err) => {
                // console.log(err);
                if (err.response.status === 404) {
                    console.log("Reroll error.");
                }
            })
    }

    // Function for Status of Bin Button
    function handleDelete() {
        setDeleteClicked(!deleteClicked);
    }

    function handleDiceDeletion() {
        axios.delete(baseURL + '/' + dice.id)
            .then(
                (response) => {
                    // console.log(response.data);
                    console.log(response.data);
                    console.log("Dice id: " + dice.id + " deleted");
                    updateDiceList();
                })
            .catch((err) => {
                // console.log(err);
                if (err.response.status === 404) {
                    console.log("No dice was found.");
                }
            })
    }


    // Status of the delete clicked
    const [deleteClicked, setDeleteClicked] = useState(false);
    const [sizeOfTheDice, setSizeOfTheDice] = useState(dice.sizeOfTheDice);
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
                alt='Dice Image'
            />

            {!deleteClicked ? <Stack>
                <CardBody>
                    <Flex>
                        <VStack>
                            <Heading size='md'>Dice:</Heading>
                            <Heading size='md'>{dice.id}</Heading>
                        </VStack>
                        <Spacer />
                        <Button variant='solid' colorScheme='blue' onClick={handleDelete}>
                            <DeleteIcon />
                        </Button>
                    </Flex>
                    <Text py='2'>
                        Rolled Number: {dice.rolledNumber}
                    </Text>

                    <Text py='2' tag={'div'}>
                        Size of the dice:
                    </Text>
                    <NumberInput tag={'div'} value={sizeOfTheDice} min={2} max={1000} onChange={(newVal) => setSizeOfTheDice(newVal)}>
                        <NumberInputField tag={'div'} />
                        <NumberInputStepper tag={'div'}>
                            <NumberIncrementStepper tag={'div'} />
                            <NumberDecrementStepper tag={'div'} />
                        </NumberInputStepper>
                    </NumberInput>


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
                    <Button variant='solid' colorScheme='blue' onClick={handleReroll}>
                        Re-roll
                    </Button>
                </CardFooter>
            </Stack>
                :
                <Stack>
                    <CardBody>
                        <Text py='2'>Do you want to delete the dice</Text>
                        <Spacer />
                        <Flex>
                            <Spacer />
                            <Button variant='solid' colorScheme='red' onClick={handleDiceDeletion}>
                                Yes
                            </Button>
                            <Spacer />
                            <Button variant='solid' colorScheme='blue' onClick={handleDelete}>
                                No
                            </Button>
                            <Spacer />
                        </Flex>
                    </CardBody>
                    <CardFooter></CardFooter>
                </Stack>
            }
        </Card>
    );
}

export default DiceCard