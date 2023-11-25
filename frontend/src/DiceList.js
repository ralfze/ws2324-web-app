import './App.css';
import axios from 'axios';
import DiceCard from './dice-card/DiceCard';
import { useState, useEffect } from 'react';
import { SimpleGrid } from '@chakra-ui/react'

function DiceList({baseURL}) {
    // Dice list of the GET request
    const [diceList, setDiceList] = useState([]);
    const renderDiceList = () => {
        if (diceList) {
            return diceList.toReversed().map(dice => <DiceCard tag={'div'} key={dice.id} dice={dice} baseURL={baseURL} updateDiceList={getDiceList}>{dice}</DiceCard>);
        }
    }

    const getDiceList = () => {
        axios.get(baseURL)
            .then(
                (response) => {
                    // console.log(response.data);
                    setDiceList(response.data);
                })
            .catch((err) => {
                // console.log(err);
                if (err.response.status === 404) {
                    console.log("No dice was found.");
                }
            })
    };
    // GET request for dice list
    useEffect(getDiceList, [])


    //if (!diceList) return null;

    return (
        <SimpleGrid columns={1} spacing={4}>
            {renderDiceList()}
        </SimpleGrid>
    );
}

export default DiceList;