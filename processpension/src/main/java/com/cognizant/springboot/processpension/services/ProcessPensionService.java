package com.cognizant.springboot.processpension.services;

import com.cognizant.springboot.processpension.entity.PensionDetail;
import com.cognizant.springboot.processpension.entity.ProcessPensionInput;

public interface ProcessPensionService {
    PensionDetail getProcessPension(ProcessPensionInput processPensionInput);
}
