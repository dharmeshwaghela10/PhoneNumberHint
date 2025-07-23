import { registerPlugin } from '@capacitor/core';

const phonenumnerhint = registerPlugin<PhoneNumberHintPlugin>('PhoneNumberHint');
export {phonenumnerhint};

export interface PhoneNumberHintPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  getPhoneNumber(): Promise<{ phoneNumber: string }>;
}

