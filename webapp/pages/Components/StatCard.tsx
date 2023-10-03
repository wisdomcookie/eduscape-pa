import React from 'react';
import PropTypes from 'prop-types';
import ArrowDownIcon from '@heroicons/react/24/outline/ArrowDownIcon';
import ArrowUpIcon from '@heroicons/react/24/outline/ArrowUpIcon';
import CurrencyDollarIcon from '@heroicons/react/24/outline/CurrencyDollarIcon';
import { Avatar, Card, CardContent, Stack, SvgIcon, Typography } from '@mui/material';
import PercentileCircle from './PercentileCirlce';

interface OverviewBudgetProps {

  difference?: number;
  positive?: boolean;
  sx?: React.CSSProperties;
  percentile?: number;
  value: string;
  title: string;
}
//FFDBAA
const OverviewBudget: React.FC<OverviewBudgetProps> = ({ difference, positive = false, sx, value, percentile,title }) => {
  
    const color = `rgb(${255 - (parseFloat(value) * 2.55)}, ${parseFloat(value) * 2.55}, 0)`;


  return (
    <Card sx={sx} >
      <CardContent>
        <Stack
          alignItems="flex-start"
          direction="row"
          justifyContent="space-between"
          spacing={3}
        >
          <Stack spacing={1}>
            <Typography
              color="text.secondary"
              variant="overline"
            >
              {title}
            </Typography>
            
            <Typography variant="h4">
              {value}
            </Typography>
          </Stack>
         {percentile && <div>
          <PercentileCircle percentile={percentile}></PercentileCircle>
          </div>}
        </Stack>
        <div style={{ marginTop: 'auto', textAlign: 'center' }}>
        {percentile && ( 
          <Stack
            alignItems="center"
            direction="row"
            spacing={2}
            sx={{ mt: 2 }}
          >
            <Stack
              alignItems="center"
              direction="row"
              spacing={0.5}
            >
              <SvgIcon
                htmlColor = { color} // Apply the color here
                fontSize="small"
              >
                {positive ? <ArrowUpIcon /> : <ArrowDownIcon />}
              </SvgIcon>
              <Typography
                color={color} // Apply the color here
                variant="body2"
              >
                {percentile}%
              </Typography>
            </Stack>
          </Stack>
        )}
        {percentile && (
          <Stack>
            
            <Typography
              color="text.secondary"
              variant="caption"
            >
              {"Better than " + percentile + "% of Schools in PA"}
            </Typography>
          </Stack>
        )}
        </div>
      </CardContent>
    </Card>
  );
};

OverviewBudget.propTypes = {
  difference: PropTypes.number,
  positive: PropTypes.bool,
  sx: PropTypes.object,
  value: PropTypes.string.isRequired
};

export default OverviewBudget;
