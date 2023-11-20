import './App.css';
import axios from 'axios';
import DiceCard from './dice-card/DiceCard';
import { useState, useEffect } from 'react';
import { SimpleGrid } from '@chakra-ui/react'




// Example Objects of a Dice List
/*
const diceList = [
    {
        "id": "1",
        "sizeOfTheDice": "6",
        "rolleNumber": 3,
        "message": "This is a message",
        "created": "2023-01-01",
        "modified": "2023-01-01"
    },
    {
        "id": "2",
        "sizeOfTheDice": "6",
        "rolleNumber": 6,
        "message": "This is a message",
        "created": "2023-01-01",
        "modified": "2023-01-01"
    },
    {
        "id": "3",
        "sizeOfTheDice": "6",
        "rolleNumber": 2,
        "message": "This is a message",
        "created": "2023-01-01",
        "modified": "2023-01-01"
    }
];

const listItems = diceList.map(person =>
    <DiceCard>{person}</DiceCard>
);
*/
function DiceList() {
    // Dice list of the GET request
    const [diceList, setDiceList] = useState([]);
    const baseURL = "http://localhost:8081/dices";
    // GET request for dice list
    useEffect(() => {
        axios.get(baseURL)
            .then(
                (response) => { setDiceList(response.data); console.log(response.data);}
            );
    }, [])
    //.catch(err => { console.log(err); });

    if (!diceList) return null;

    return (
        <SimpleGrid columns={1} spacing={4}>
            {diceList.toReversed().map(dice => <DiceCard dice={dice}>{dice}</DiceCard>)}
        </SimpleGrid>
    );
}

export default DiceList;