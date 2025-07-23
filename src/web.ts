import { WebPlugin } from '@capacitor/core';

import type { PhoneNumberHintPlugin } from './definitions';

export class PhoneNumberHintWeb extends WebPlugin implements PhoneNumberHintPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }

    async phonenumnerhint(options:{value:string}):Promise<{value:string}>{
    console.log('phonenumnerhint', options);
    console.log('test')
    return options
  }
  

   async getPhoneNumber(): Promise<{ phoneNumber: string }> {
    console.warn('PhoneNumberHint is not available on web.');
    return { phoneNumber: '' };
  }
}
