export interface PhoneNumberHintPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
