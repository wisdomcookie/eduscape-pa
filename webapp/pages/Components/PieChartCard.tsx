import React from 'react';
import PropTypes from 'prop-types';
import ArrowDownIcon from '@heroicons/react/24/outline/ArrowDownIcon';
import ArrowUpIcon from '@heroicons/react/24/outline/ArrowUpIcon';
import CurrencyDollarIcon from '@heroicons/react/24/outline/CurrencyDollarIcon';
import { Avatar, Card, CardContent, Stack, SvgIcon, Typography } from '@mui/material';

import { PieChart } from 'react-minimal-pie-chart';
import PercentileCircle from './PercentileCirlce';

interface OverviewBudgetProps {
  difference?: number;
  positive?: boolean;
  sx?: React.CSSProperties;
  percentile: number;
  data: any[]; // Add a prop for the pie chart data
  title: string;
}
const chartStyle = {
  width: '65%',
  height: '65%',
  margin: 'auto'
// Each chart takes up 45% of the container
};


const COLORS = ['#0088FE', '#FFBB28']; // Use only two colors

const PieChartCard: React.FC<OverviewBudgetProps> = ({ difference, positive = false, sx, data, percentile, title }) => {
  return (
    <Card >
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
            <div className='test chart' style={chartStyle}>
              <PieChart data={data} />
            </div>
          </Stack>
          <div>
          <PercentileCircle percentile={percentile}></PercentileCircle>
          </div>
          
        </Stack>
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
                color={positive ? 'success' : 'error'}
                fontSize="small"
              >
                {positive ? <ArrowUpIcon /> : <ArrowDownIcon />}
              </SvgIcon>
              <Typography
                color={positive ? 'success.main' : 'error.main'}
                variant="body2"
              >
                {percentile}%
              </Typography>
            </Stack>
            <Typography
              color="text.secondary"
              variant="caption"
            >
              {"Better than " + percentile + "% of Schools in PA"}
            </Typography>
          </Stack>
        )}
      </CardContent>
    </Card>
  );
};

PieChartCard.propTypes = {
  difference: PropTypes.number,
  positive: PropTypes.bool,
  sx: PropTypes.object,
  data: PropTypes.array.isRequired, // Add prop type for pie chart data
};

export default PieChartCard;
