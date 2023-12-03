import React, { useState } from 'react';
import { Button, List, ListItem, ListItemText, Paper, Typography } from '@mui/material';


interface SortableListProps {
    sortedItems: string[]; // Changed from 'items' to 'sortedItems'
    setSortedItems: React.Dispatch<React.SetStateAction<string[]>>; 
    handleClicked: () => void;  // Added prop for updating sortedItems
  }

const SortableList: React.FC<SortableListProps> = ({ sortedItems,setSortedItems,handleClicked }) => {


  const handleDragStart = (e: React.DragEvent<HTMLLIElement>, index: number) => {
    e.dataTransfer.setData('text/plain', index.toString());
  };

  const handleDragOver = (e: React.DragEvent<HTMLLIElement>) => {
    e.preventDefault();
  };

  const handleDrop = (e: React.DragEvent<HTMLLIElement>, index: number) => {
    const draggedItemIndex = Number(e.dataTransfer.getData('text/plain'));
    const updatedItems = [...sortedItems];
    const [draggedItem] = updatedItems.splice(draggedItemIndex, 1);
    updatedItems.splice(index, 0, draggedItem);
    setSortedItems(updatedItems);
  };

  return (
    <div>

<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Oleo+Script:wght@400&display=swap" />
      <Typography variant="h4" style={{ fontFamily: 'Oleo Script, cursive',marginBottom: '16px' }}>Ranking Criteria</Typography>
      <List>
        {sortedItems.map((item, index) => (
          <div key={index} style={{ display: 'flex', alignItems: 'center', marginBottom: '8px' }}>
            <Typography variant="h5" style={{ fontWeight: 'bold', marginRight: '8px' }}>{`${index + 1}.`}</Typography>
            <Paper
              elevation={3}
              style={{
                padding: '8px',
                borderRadius: '8px',
                display: 'flex',
                alignItems: 'center',
                width: '100%',
              }}
            >
              <ListItem
                draggable
                onDragStart={(e) => handleDragStart(e, index)}
                onDragOver={handleDragOver}
                onDrop={(e) => handleDrop(e, index)}
                style={{ flex: 1 }}
              >
                <ListItemText primary={item} />
              </ListItem>
            </Paper>
          </div>
        ))}
      </List>
      <Button 
        variant="contained" 
        color="primary" 
        onClick={handleClicked} 
        style={{ backgroundColor: '#96D691' }} // Set the background color to blue
      >
        Submit Changes
      </Button>
    </div>
  );
};

const SortableListContainer: React.FC<SortableListProps> = ({ sortedItems,setSortedItems,handleClicked })=> {
  

  return (
    <div style={{ maxWidth: '400px', margin: '0 auto', padding: '0 16px' }}>
      <SortableList 
        sortedItems={sortedItems} 
        setSortedItems={setSortedItems} 
        handleClicked={handleClicked}
      />
    </div>
  );
};

export default SortableListContainer;
